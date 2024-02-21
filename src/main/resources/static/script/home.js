// Location my server is running on
let apiURL = 'http://localhost:8080';

async function fetchAllSoda(){
    let response = await fetch(apiURL + "/items");

    if (response.status === 200) {
        let data = await response.json();
        populateSodas(data);
    } else {
        console.log("Error fetching all sodas.")
    }
}

async function fetchSodaByCategory(e){
    const category = e.target.value;
    let response = await fetch(apiURL + "/items/categories/" + category);
    if (response.status === 200) {
        let data = await response.json();
        populateSodas(data);
    } else {
        console.log("Error fetching sodas by category.")
    }
}

async function fetchSodaBySeller(e){
    const seller = e.target.value;
    let response = await fetch(apiURL + "/items/sellers/" + seller);
    if (response.status === 200) {
        let data = await response.json();
        populateSodas(data);
    } else {
        console.log("Error fetching sodas by seller.")
    }
}

async function fetchCart(){
    let account = JSON.parse(localStorage.getItem("account"));
    let response = await fetch(apiURL + "/cart/accounts/" + account.account_id);
    if (response.status === 200) {
        return await response.json();
    } else {
        console.log("Error fetching cart")
    }
}

function logOut(){
    localStorage.setItem('account', null);
    localStorage.setItem("loggedIn", "false");
    location.reload();
}

function populateSodas(sodas){
    let sodaNode = document.getElementById('sodas');
    sodaNode.innerHTML = "";
    sodas.forEach((soda) => {
        //console.log(soda);
        const sodaContainer = document.createElement("div");
        sodaContainer.className = "sodaContainer";
        sodaContainer.setAttribute("item_id", soda.item_id);

        let sodaTitle = document.createElement("h4");
        let sodaName = document.createTextNode(soda.name);
        sodaTitle.appendChild(sodaName);

        let sodaImage = document.createElement("img");
        sodaImage.src = soda.imageLocation;

        let sodaPrice = document.createElement("p");
        let sodaPriceText = document.createTextNode(soda.price);
        sodaPrice.appendChild(sodaPriceText);

        let sodaDescription = document.createElement("p");
        let sodaDescriptionText = document.createTextNode(soda.description);
        sodaDescription.appendChild(sodaDescriptionText);

        //Create the add button, designed to be modified later
        let cartDiv = document.createElement("div");
        cartDiv.className = "cartInterface";
        let addButton = document.createElement("button");
        addButton.textContent = "Add to Cart";
        addButton.value = soda.item_id;
        addButton.onclick = addToCart;
        cartDiv.appendChild(addButton);

        sodaContainer.appendChild(sodaTitle);
        sodaContainer.appendChild(sodaImage);
        sodaContainer.appendChild(sodaPrice);
        sodaContainer.appendChild(sodaDescription);
        sodaContainer.appendChild(cartDiv);
        sodaNode.appendChild(sodaContainer); //Add the new soda element to the div on the page
    });
}

async function addToCart(event){
    const account = JSON.parse(localStorage.getItem("account"))
    if (localStorage.getItem("loggedIn") == "true"){
        await fetch(apiURL + `/cart/entries/${account.account_id}/${event.target.value}`, {method: "POST"});
        updatecartDisplay()
    }
    else {
        alert("Please log in to add sodas to your cart")
    }
}

function logginLoggout(){
    if (localStorage.getItem("loggedIn") == "true"){
        logOut();
    }
    else{
        window.location.href = "http://localhost:8080/login.html";
    }
}

async function updatecartDisplay(){
    const cart = await fetchCart();
    const cartDisplay = document.getElementById("cartDisplay");
    let total = 0;
    for(let i = 1; i < cart.length; i += 2){
        total += cart[i];
    }
    if (total === 0){
        cartDisplay.textContent = "Cart";
    }
    else{
        cartDisplay.textContent = "Cart (" + total + ")";
    }
}

function cartRedirect(){
    window.location.href = apiURL + "/cart.html";
}

//Should always run when page is first loaded
fetchAllSoda();
if(localStorage.getItem("loggedIn") == "true"){
    document.getElementById("loginButton").textContent = "Log Out"
    updatecartDisplay()
}
else{
    document.getElementById("loginButton").textContent = "Log In"
}
console.log("Logged in: " + localStorage.getItem("loggedIn"));