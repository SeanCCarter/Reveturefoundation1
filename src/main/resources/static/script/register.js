// Location my server is running on
let apiURL = 'http://localhost:8080';
const registerForm = document.getElementById('registerForm');
registerForm.addEventListener("submit", registerUser);

async function registerUser(event) {
    event.preventDefault();
    const formData = new FormData(event.target);
    console.log('Attempting Login')

    const accountSubmission = {
        username: formData.get('username'),
        password: formData.get('password')
    }

    console.log(accountSubmission)

    let response = await fetch(apiURL + "/register", {
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
    const feedback = document.getElementById('registerResponse');
    feedback.textContent = "User could not be created! Try another Username!"
}

function redirectHome(){
    window.location.href = "http://localhost:8080";
}