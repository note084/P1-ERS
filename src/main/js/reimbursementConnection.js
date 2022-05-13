
let reimbursementResourceURL = "http://localhost:8080/ERS/reimbursement";
let editReimbursementResourceURL = "http://localhost:8080/ERS/reimbursement/edit";
let manageReimbursementResourceURL = "http://localhost:8080/ERS/reimbursement/manage";
let sortReimbursementResourceURL = "http://localhost:8080/ERS/reimbursement/sort";


async function newReimbursement(newReimbursement) {
    let response = await fetch(
        reimbursementResourceURL,
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(newReimbursement)
        }
    );

    return response;
}

async function getAllReimbursementsByID(id) {
    let response = await fetch(
        reimbursementResourceURL,
        {
            method: "GET",
            headers: {
                id: id
            }
        }
    );
    return response;
}

async function getReimbursement(id) {
    let response = await fetch(
        editReimbursementResourceURL,
        {
            method: "GET",
            headers: {
                id: id
            }
        }
    );
    return response;
}

async function listReimbursementsByUsername(username) {
    let response = await fetch(
        reimbursementResourceURL,
        {
            method: "GET",
            headers: {
                username: username
            }
        }
    )
}

async function updateReimbursement(reimbursement) {
    let response = await fetch(
        editReimbursementResourceURL,
        {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(reimbursement)
        }
    );

    return response;
}

async function deleteReimbursement(id) {
    let response = await fetch(
        reimbursementResourceURL,
        {
            method: "DELETE",
            headers: {
                id: id
            }
        }
    );
    return response;
}

async function getAllReimbursements (){
    let response = await fetch(
        manageReimbursementResourceURL,
        {
            method: "GET",
        }
    );
    return response;
}

async function getByStatus(status){
    let response = await fetch(
        sortReimbursementResourceURL,
        {
            method: "GET",
            headers: {
                status: status
            }
        }
    );
    return response;
}

async function resolveReimbursement (data) {
    let response = await fetch(
        manageReimbursementResourceURL,
        {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(data)
        }
    )
    return response;
}