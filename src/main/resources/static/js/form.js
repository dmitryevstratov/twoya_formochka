//Display values
const DISPLAY_NONE = "none";
const DISPLAY_BLOCK = "block";

//Class names
const CLASSNAME_FADE = "fade";
const CLASSNAME_SHOW = "show";
const MODAL_BACKDROP = "modal-backdrop";
const MODAL_OPEN = "modal-open";

function clearForm(idForm) {
    if (idForm != null) {
        let form = document.querySelector("#" + idForm);
        form.querySelectorAll("input").forEach(e => {
            if (e.type === "text" || e.type === "number" || e.type === "email" || e.type === "tel" || e.type === "date") {
                e.value = EMPTY_VALUE;
            }
        });
        form.querySelectorAll("textarea").forEach(e => e.value = EMPTY_VALUE);
    }
}

function hiddenForm(id) {
    document.body.classList.remove(MODAL_OPEN);
    document.querySelector("#" + id).style.display = DISPLAY_NONE;
    document.querySelector("#" + id).classList.remove(CLASSNAME_SHOW);
    document.body.style.overflow = "visible";

    document.querySelectorAll("." + MODAL_BACKDROP)
        .forEach(element => element.style.display = DISPLAY_NONE);

    switch (id) {
        case 'addOrder':
            clearOrderForm("");
            break;
        case 'editOrder':
            clearOrderForm(SUFFIX_EDIT_FIELD);
            break;
        case 'deleteOrder':
            clearOrderForm(SUFFIX_DELETE_FIELD);
            break;
        default:
            clearForm(id);
    }
}

function openForm(id) {
    document.querySelector("#" + id).classList.add(CLASSNAME_SHOW);
    document.querySelector("#" + id).style.display = DISPLAY_BLOCK;

    if (document.querySelector("." + MODAL_BACKDROP) == null) {
        let div = document.createElement('div');
        div.style.display = DISPLAY_BLOCK;
        div.className = MODAL_BACKDROP;
        div.classList.add(CLASSNAME_SHOW);
        div.classList.add(CLASSNAME_FADE);
        document.body.append(div);
    } else {
        document.querySelector("." + MODAL_BACKDROP).style.display = DISPLAY_BLOCK;
    }

}

//Fetch

async function fetchSendData(url = '', data = {}, method) {
    const response = await fetch(url, {
        method: method,
        headers: {
            'Content-Type': CONTENT_TYPE_JSON
        },
        body: JSON.stringify(data)
    });
    return await response.json();
}