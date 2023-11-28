fetch("http://localhost:8088/api/user")
    .then((response) => {
        return response.json();
    })
    .then((user) => {
        let tableUser = `<tr> 
              <td>${user.id}</td>
              <td>${user.login}</td>
              <td>${user.name}</td>
              <td>${user.lastname}</td>
              <td>${user.age}</td>
              <td>${user.email}</td>
              <td>${user.roleUserToString}</td>
              </tr>`
        document.getElementById('tableUserInfo').innerHTML = tableUser

        let headerSpan = `<b><span class="navbar-brand text-white">${user.login}</span></b>
                            <span class="navbar-brand text-white" style="margin-left: -15px;"> with role: ${user.roleUserToString}</span>`
        document.getElementById("headerSpan").innerHTML = headerSpan

        if (user.roleUserToString.indexOf("ADMIN")) {
            document.getElementById("adminButton").style.display = "none";
        }
    });