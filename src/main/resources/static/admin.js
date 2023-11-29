            // Show all users
fetch("http://localhost:8088/api/user")
    .then((response => response.json())).then((user => {
    let headerSpan = `<b><span class="navbar-brand text-white">${user.login}</span></b>
                            <span class="navbar-brand text-white" style="margin-left: -15px;"> with role: ${user.roleUserToString}</span>`
    document.getElementById("headerSpan").innerHTML = headerSpan
}))

function adminPage() {
    let tableAllUsers = ''
    fetch("http://localhost:8088/api/admin").then((response => response.json()))
        .then((users => {
            for (let user of users) {
                tableAllUsers +=
                    `<tr>
                        <td>${user.id}</td>
                        <td>${user.login}</td>
                        <td>${user.name}</td>
                        <td>${user.lastname}</td>
                        <td>${user.age}</td>
                        <td>${user.email}</td>
                        <td>${user.roleUserToString}</td>
                        
                        <td><button type="button" class="btn btn-info" data-toggle="modal"
            data-target="#editModal" onclick="editModal(${user.id})">Edit</button></td>
            
            <td><button type="button" class="btn btn-danger" data-toggle="modal"
                        data-target="#deleteModal" onclick="deleteModal(${user.id})">
                    Delete</button></td>
            </tr>`
            }
            document.getElementById("tableAllUsers").innerHTML = tableAllUsers
        }))
}

adminPage()

            // Add new user

const formNewUser = document.getElementById("addUser")
formNewUser.addEventListener("submit", newUser => {
    newUser.preventDefault()
    let roles = []
    for (let i = 0; i < document.querySelector("#role").selectedOptions.length; i++) {
        roles.push({
            id: document.querySelector("#role").selectedOptions[i].value
        })
    }
    fetch("http://localhost:8088/api/admin/new", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            login: formNewUser.login.value,
            name: formNewUser.name.value,
            lastname: formNewUser.lastname.value,
            age: formNewUser.age.value,
            email: formNewUser.email.value,
            password: formNewUser.password.value,
            roles: roles
        })
    })
    .then(() => {
        formNewUser.reset();
        adminPage();
        document.getElementById("tableAdmin").click();
    })
})

            // Delete user

const formDeleteUser = document.getElementById("deleteForm")
function deleteModal(id) {
    fetch("http://localhost:8088/api/admin/show?id=" + id).then(response => response.json())
        .then(deleteUser => {
            formDeleteUser.d_id.value = deleteUser.id
            formDeleteUser.d_login.value = deleteUser.login
            formDeleteUser.d_name.value = deleteUser.name
            formDeleteUser.d_lastname.value = deleteUser.lastname
            formDeleteUser.d_age.value = deleteUser.age
            formDeleteUser.d_email.value = deleteUser.email
            console.log(deleteUser.id)
        })
}

formDeleteUser.addEventListener("submit", deleteUser => {
    deleteUser.preventDefault()
    fetch("http://localhost:8088/api/admin/delete?id=" + formDeleteUser.d_id.value, {
        method: "DELETE"
    })
    .then(() => {
        adminPage();
        document.getElementById("deleteModalClose").click();
    })
})

                // Edit user
const formEditUser = document.getElementById("editForm")
function editModal(id) {
    fetch("http://localhost:8088/api/admin/show?id=" + id).then(response => response.json())
        .then(editUser => {
            formEditUser.e_id.value = editUser.id
            formEditUser.e_login.value = editUser.login
            formEditUser.e_name.value = editUser.name
            formEditUser.e_lastname.value = editUser.lastname
            formEditUser.e_age.value = editUser.age
            formEditUser.e_email.value = editUser.email
            formEditUser.e_password.value = editUser.password
        })
}

formEditUser.addEventListener("submit", editUser => {
    editUser.preventDefault()
    let roles = []
    for (let i = 0; i < document.querySelector("#roleEdit").selectedOptions.length; i++) {
        roles.push({
            id: document.querySelector("#roleEdit").selectedOptions[i].value
        })
    }
    fetch("http://localhost:8088/api/admin/edit?id=" + formEditUser.e_id.value, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            id: formEditUser.e_id.value,
            login: formEditUser.e_login.value,
            name: formEditUser.e_name.value,
            lastname: formEditUser.e_lastname.value,
            age: formEditUser.e_age.value,
            email: formEditUser.e_email.value,
            password: formEditUser.e_password.value,
            roles: roles
        })
    })
    .then(() => {
        adminPage();
        document.getElementById("editModalClose").click();
    })
})