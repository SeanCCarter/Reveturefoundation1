// Location my server is running on
let apiURL = 'http://localhost:8080';

async function deleteCartEntry(e){
    e.target.value
    console.log("deleting cart entry")
    let account = JSON.parse(localStorage.getItem("account"));
    let response = await fetch(apiURL + "/cart/entries/" + account.account_id + "/" + e.target.value, {
        method : "DELETE"
    });
    if (response.status === 200) {
        await response.json();
        populateCart();
    } else {
        console.log("Error deleting cart entry")
    }
}

async function updateCartEntry(e){
    console.log("updating cart entry")
    console.log(e.target.value)
    let account = JSON.parse(localStorage.getItem("account"));
    let response = await fetch(apiURL + "/cart/entries/" + account.account_id + "/" + e.target.getAttribute("item_id"), {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json",
          },
        body: JSON.stringify({"newQuantity": e.target.value})
    });
    if (response.status === 200) {
        await response.json();
        populateCart();
    } else {
        console.log("Error updating cart entry")
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

async function populateCart(){
    console.log("Populating cart")
    let cart = await fetchCart();
    let cartTable = document.getElementById('cartTableBody');
    cartTable.innerHTML = "";

    let totalCans = 0;
    let totalCost = 0;
    for (let i = 0; i<cart.length; i+=2){
        const newPrice = cart[i].price*cart[i+1];
        totalCans += cart[i+1];
        totalCost += newPrice;
        let row = document.createElement("tr");

        let sodaName = document.createElement("td")
        sodaName.textContent = cart[i].name;
        row.appendChild(sodaName);

        let sodaQuantity = document.createElement("td")
        let sodaQEditor = document.createElement("input")
        sodaQEditor.type = "number";
        sodaQEditor.value = cart[i + 1];
        sodaQEditor.min = 1; 
        sodaQEditor.setAttribute("item_id", cart[i].item_id);
        sodaQEditor.addEventListener("change", updateCartEntry);
        sodaQuantity.append(sodaQEditor);
        row.appendChild(sodaQuantity);

        let sodaPrice = document.createElement("td")
        sodaPrice.textContent = Math.round(newPrice*100)/100;
        row.appendChild(sodaPrice);

        //Create the add button, designed to be modified later
        let removeButton = document.createElement("button");
        removeButton.textContent = "Remove";
        removeButton.value = cart[i].item_id;
        removeButton.onclick = deleteCartEntry;
        row.appendChild(removeButton); //Add the new soda element to the div on the page

        cartTable.appendChild(row);
    }
    document.getElementById('totalCans').textContent = totalCans;
    document.getElementById('totalCost').textContent = Math.round(totalCost * 100) / 100; //Fix rounding errors
}

function redirectHome(){
    window.location.href = "http://localhost:8080";
}
function redirectCheckout(){
    window.location.href = "http://localhost:8080/checkout.html";
}

populateCart();