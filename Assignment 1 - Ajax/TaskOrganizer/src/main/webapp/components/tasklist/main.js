export default class extends HTMLElement {
    #shadow;
    #statuslist;
    #cssfile = "main.css";

    constructor() {
        // Always call super first in constructor
        super();

        // Entry point to the shadow DOM
        // If open, property "shadowRoot" will be an outside entrance to the shadow DOM
        this.#shadow = this.attachShadow({ mode: 'closed' });
        this.#createLink();
        this.#createHTML();
        
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
        const content = `
            <p id="p1"> Waiting for server data </p> <br><br>

            <button type="button" id="newTask1" disabled>New Task</button>
        `;

        const wrapper = document.createElement('div');
        wrapper.insertAdjacentHTML('beforeend', content);
        this.#shadow.appendChild(wrapper);
        return wrapper;
    }

    //input is the object that contains parameters
    showTask(newTask) {
        const table = this.#shadow.getElementById('table1');

        if(table == null){
            const p1 = this.#shadow.getElementById('p1');
            p1.remove();

            const content = `
                <table id="table1">
                    <tr>
                        <th>Tasks</th>
                        <th>Status</th>
                        <th></th>
                        <th></th>
                    </tr>
                    <tr id=${newTask.id}>
                        <td>${newTask.title}</td>
                        <td>${newTask.status}</td>
                        <td><select id=${newTask.id} class="selectlist"><option value="modify">&#60Modify&#62</option></select></td>
                        <td><button class="remove-btn" type="button" id=${newTask.id}>Remove</button></td>
                    </tr>
                </table>
            `;
            const wrapper = document.createElement('div');
            wrapper.insertAdjacentHTML('beforeend', content);
            this.#shadow.appendChild(wrapper);   
            
            var select = this.#shadow.querySelector(".selectlist");
            this.#statuslist.forEach(value => {
                var option = document.createElement("option");
                option.text = value;
                select.add(option);
            });
        }

        else{
            var row = table.insertRow(1);
            row.setAttribute("id",newTask.id);
            row.setAttribute("stdid", newTask.id);

            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            var cell4 = row.insertCell(3);

            cell1.innerHTML = newTask.title;
            cell2.innerHTML = newTask.status;

            var select = document.createElement("select");
            select.id = newTask.id;

            var option = document.createElement("option");
            option.text = "<Modify>";
            select.add(option);
            this.#statuslist.forEach(value => {
                var option = document.createElement("option");
                option.text = value;
                select.add(option);
            });
            cell3.append(select);

            let btn = document.createElement("button");
            btn.innerText = "Remove";
            btn.className = "remove-btn";
            btn.id = newTask.id;

            cell4.append(btn);   
        }
 
    }

    removeTask(id){
        const row = this.#shadow.getElementById(id);
        row.remove();
    }
    
    updateTask(status){
        const row = this.#shadow.getElementById(status.id);

        if (condition) {
            
        }

        row.deleteCell(1);
        var cell1 = row.insertCell(1);
        cell1.innerHTML = status.status;

    }

    setStatuseslist(statuslist){ 
        this.#statuslist = statuslist;
    }

    enableaddtask(){
        this.#shadow.getElementById("newTask1").disabled = false;
    }


    addtaskCallback(cb){
        this.#shadow.getElementById("newTask1").addEventListener("click", cb);
    }

    changestatusCallback(method){

        const selectlist = this.#shadow.querySelectorAll('select');

        selectlist.forEach(select => {
            select.addEventListener("change", (event) => {

                const task = this.#shadow.getElementById(event.target.id).cells[0].innerText;

                var response = confirm("Set "  + task + " to " + event.target.value);

                if(response == true){
                    method(event.target.id, event.target.value);
                }

            })
        });

    }

    deletetaskCallback(callback){

        this.#shadow.querySelectorAll('.remove-btn').forEach((deletebtn) => {
            deletebtn.addEventListener('click', (event) => {
                const task = this.#shadow.getElementById(event.target.id).cells[0].innerText;
                
                let confirm = window.confirm("Remove task " + task + "?")
                if(confirm) {
                    callback(event.target.id);
                }

            });
        });

    }
    
    noTask(){
        this.#shadow.getElementById('p1').innerText = "No tasks were found.";
    }
}