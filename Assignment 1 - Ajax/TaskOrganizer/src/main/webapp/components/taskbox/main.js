export default class extends HTMLElement {
    #shadow;
    #cssfile = "main.css";
    #statuses;

    constructor() {
        // Always call super first in constructor
        super();

        // Entry point to the shadow DOM
        // If open, property "shadowRoot" will be an outside entrance to the shadow DOM
        this.#shadow = this.attachShadow({ mode: 'closed' });
        this.#createLink();
        this.#createHTML();

        //for close action on click and escape
        const taskBox = this;
        var span = taskBox.#shadow.querySelector(".close");

        span.addEventListener('click', function() {
            taskBox.close();
        });

        document.body.addEventListener('keydown', (e) => {
            if(e.key === "Escape") {
                taskBox.close();
            }
        });
        
    }

    #createLink() {
        const link = document.createElement('link');

        // Use directory of script as directory of CSS file
        //const path = document.currentScript.src.match(/.*\//)[0];
        const path = import.meta.url.match(/.*\//)[0];
        link.href = path.concat(this.#cssfile);
        link.rel = "stylesheet";
        link.type = "text/css";
        this.#shadow.appendChild(link);
        return link;
    }

    #createHTML() {
        //modal from sometwhere on web
        const content = `
        <!-- Modal content -->
        <dialog class="modal-content" id="newtask-modal">
            <span class="close">&times;</span>

            <label for="input-field">Title: </label>
                <input type="text" class="input-field" name="input-field"><br><br>
            <label for="status-select">Status: </label>
            
            <select class="status-select"></select>

            <br>
            <br>
            <button id="modal-button">Add Task</button>
        </dialog>
        `;
    
        const wrapper = document.createElement('div');
        wrapper.insertAdjacentHTML('beforeend', content);
        this.#shadow.appendChild(wrapper);
        return wrapper;
    }


    show(){
        this.#shadow.querySelector('.status-select').innerHTML = "";

        this.#statuses.forEach((status) => {
            //first: text, second: value
            const option = new Option(status, status);
            this.#shadow.querySelector('.status-select').appendChild(option);
        });

        let modal = this.#shadow.querySelector('#newtask-modal');
        modal.showModal();
    }

    setStatuseslist(list){
        this.#statuses = list;
    }

    newtaskCallback(callback){
        const shadow = this.#shadow;
        shadow.getElementById('modal-button').addEventListener('click', function() {
            let task = {
                title : shadow.querySelector(".input-field").value,
                status : shadow.querySelector(".status-select").value
            };
            shadow.querySelector(".input-field").value = "";
            callback(task);
        });
    }

    close(){
        this.#shadow.querySelector('#newtask-modal').close();
    }
}