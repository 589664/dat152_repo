import tasklist from '../tasklist/main.js';
import taskbox from '../taskbox/main.js';
customElements.define('task-list', tasklist);
customElements.define('task-box', taskbox);

export default class extends HTMLElement {
    #shadow;
    #cssfile = "main.css";

    //private lists for tasks and boxes on page
    #tasklists
    #taskboxes

    constructor() {
        // Always call super first in constructor
        super();

        // Entry point to the shadow DOM
        this.#shadow = this.attachShadow({ mode: 'closed' });
        this.#createLink();
        this.#createHTML();

        //get all of them and put them into the lists from page
        this.#tasklists = this.#shadow.querySelectorAll('task-list');
        this.#taskboxes = this.#shadow.querySelectorAll('task-box');
        
        /**
         * fetcing tasks from backend and adding them to front end table
         * as well as adding callbacks
         */
        this.#getTasklist();
        /**
         * fetching statuses stored in backend and adding them to select
         * options on tasklist and taskbox on frontend
         */
        this.#getAllstatuses();

        /**
         * adding callback on newtask button so it will show 
         * taskbox on click for each tasklist corresponding taskbox
         */
        const taskboxes = this.#taskboxes;

        this.#tasklists.forEach((tasklist) => {
            var cnt = 0;
            tasklist.addtaskCallback(function() {
                taskboxes[cnt].show();
                cnt++;
            });
        });

        /**
         * adding callbacks on taskboxes so it will post a task
         * to backend if user descides to add task from frontend 
         * taskbox
         */
        this.#taskboxes.forEach((taskbox) => {
            taskbox.newtaskCallback(this.#postTask.bind(this));
            taskbox.newtaskCallback(function() {
                taskbox.close();
            });
        });

    }


    #createLink() {
        const link = document.createElement('link');
        const path = import.meta.url.match(/.*\//)[0];
        link.href = path.concat(this.#cssfile);
        link.rel = "stylesheet";
        link.type = "text/css";
        this.#shadow.appendChild(link);
    }

    #createHTML() {
        const wrapper = document.createElement('div');
        wrapper.id = "wrapper";

        const content = `
        <task-list></task-list>
        <task-box></task-box>

        <task-list></task-list>
        <task-box></task-box>
        
        <task-list></task-list>
        <task-box></task-box>
        `;

        wrapper.insertAdjacentHTML('beforeend', content);
        this.#shadow.appendChild(wrapper);

        return wrapper;
    }

    async #getAllstatuses() {
        try {
            const response = await fetch('../TaskServices/api/services/allstatuses');
            if (response.ok) {
                const object = await response.json();
                if (typeof object.responseStatus != "undefined") {
                    if (object.responseStatus) {
                        this.#taskboxes.forEach((taskbox) => {
                            taskbox.setStatuseslist(object.allstatuses);
                        });
                        this.#tasklists.forEach((tasklist) => {
                            tasklist.setStatuseslist(object.allstatuses);
                        });
                    } else {
                        console.log("Could not connect to server");
                    }
                } else {
                    console.log("Could not connect to server");
                }
            }
        } catch (e) {
            console.log("Could not connect to server");
        }
    }


    async #getTasklist() {
        try {
            const response = await fetch('../TaskServices/api/services/tasklist');
            if (response.ok) {
                const object = await response.json();
                if (typeof object.responseStatus != "undefined") {
                    if (object.responseStatus) {
                        this.#tasklists.forEach((tasklist) => {
                            console.log(object);
                            object.tasks.forEach((task) => {
                                tasklist.showTask(task);
                            });
                            if(object.tasks.length == 0) {
                                tasklist.noTask();
                            }
                            tasklist.enableaddtask();
                            tasklist.changestatusCallback(this.#putTask.bind(this));
                            tasklist.deletetaskCallback(this.#deleteTask.bind(this));
                        });
                    } else {
                        console.log("Could not connect to server");
                    }
                } else {
                    console.log("Could not connect to server");
                }
            }
        } catch (e) {
            console.log("Could not connect to server. Reason: " + e);
        }
    }

    async #postTask(task) {
        const requestSettings = {
            "method": "POST",
            "headers": { "Content-Type": "application/json; charset=utf-8" },
            "body": JSON.stringify(task),
            "cache": "no-cache",
            "redirect": "error"
        };

        try {
            const response = await fetch("../TaskServices/api/services/task", requestSettings);
            if (response.ok) {
                const object = await response.json();
                if (typeof object.responseStatus != "undefined") {
                    if (object.responseStatus) {
                        this.#tasklists.forEach((tasklist) => {
                            tasklist.showTask(object.task);
                        });
                    } else {
                        console.log("Could not connect to server");
                    }
                } else {
                    console.log("Could not connect to server");
                }
            }
        } catch (e) {
            console.log("Could not connect to server. Because: " + e);
        }
    }

    async #putTask(id, status) {
        const obj = {
            "status" : status
        }
        const requestSettings = {
            "method": "PUT",
            "headers": { "Content-Type": "application/json; charset=utf-8" },
            "body": JSON.stringify(obj),
            "cache": "no-cache",
            "redirect": "error"
        };

        try {
            const response = await fetch("../TaskServices/api/services/task/" + id, requestSettings);
            if (response.ok) {
                const object = await response.json();
                if (typeof object.responseStatus != "undefined") {
                    if (object.responseStatus) {
                        const newStatus = {
                            "status" : object.status,
                            "id" : object.id
                        }
                        this.#tasklists.forEach((tasklist) => {
                            tasklist.updateTask(newStatus);
                        });
                    } else {
                        console.log("Could not connect to server");
                    }
                } else {
                    console.log("Could not connect to server");
                }
            }
        } catch (e) {
            console.log("Could not connect to server. Because: " + e);
        }
    }

    async #deleteTask(id) {
        const requestSettings = {
            "method": "DELETE",
            "headers": { "Content-Type": "application/json; charset=utf-8" },
            "cache": "no-cache",
            "redirect": "error"
        };

        try {
            const response = await fetch("../TaskServices/api/services/task/" + id, requestSettings);
            if (response.ok) {
                const object = await response.json();
                if (typeof object.responseStatus != "undefined") {
                    if (object.responseStatus) {
                        this.#tasklists.forEach((tasklist) => {
                            tasklist.removeTask(id);
                        });
                    } else {
                        console.log("Could not connect to server");
                    }
                } else {
                    console.log("Could not connect to server");
                }
            }
        } catch (e) {
            console.log("Could not connect to server. Because: " + e);
        }
    }
}