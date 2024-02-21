// Location my server is running on
let apiURL = 'http://localhost:8080';
const loginForm = document.getElementById('loginForm');
loginForm.addEventListener("submit", verifyLogin);

async function verifyLogin(event) {
    event.preventDefault();
    const formData = new FormData(event.target);
    console.log('Attempting Login')

    const accountSubmission = {
        username: formData.get('username'),
        password: formData.get('password')
    }

    console.log(accountSubmission)

    let response = await fetch(apiURL + "/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
          },
        body: JSON.stringify(accountSubmission)
    });

    if (response.status === 200) {
        let accountData = await response.json();
        handleSuccessfullLogin(accountData); 
    } else {
        handleUnsuccessfulLogin(response)
    }
}

function handleSuccessfullLogin(accountData){
    console.log("Successful Login");
    localStorage.setItem('account', JSON.stringify(accountData));
    localStorage.setItem("loggedIn", "true");
    window.location.href = "http://localhost:8080";
}

function handleUnsuccessfulLogin(response){
    if (response.status === 401) {
        const feedback = document.getElementById('loginResponse');
        feedback.textContent = "Incorrect Username or Password!"
    } else {
        const feedback = document.getElementById('loginResponse');
        feedback.textContent = "There was a problem logging in"
    }
}