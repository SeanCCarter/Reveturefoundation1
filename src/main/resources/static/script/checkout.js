// Location my server is running on
let apiURL = 'http://localhost:8080';

async function deleteCart(){
    let account = JSON.parse(localStorage.getItem("account"));
    let response = await fetch(apiURL + "/cart/accounts/" + account.account_id, {
        method : "DELETE"
    });
    if (response.status === 200) {
        console.log("Cart deleted")
    } else {
        console.log("Error deleting cart")
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

async function getCartTotal(){
    let cart = await fetchCart();

    let totalCost = 0;
    for (let i = 0; i<cart.length; i+=2){
        totalCost += cart[i].price*cart[i+1];
    }
    return Math.round(totalCost * 100) / 100; //Fix rounding errors
}

function redirectHome(){
    window.location.href = "http://localhost:8080";
}

async function handleSubmit(){
    console.log("submitting")
    form = document.getElementById("CheckoutForm");
    if(form.checkValidity()){
        const totalCost = await getCartTotal();
        deleteCart();
        alert(`Thank you for your order of $${totalCost} worth of soft drinks!`);
    }
    else{
        console.log("Fix form.")
    }
}
function handleSubmitAndRedirect(){
    handleSubmit();
    console.log("handling submit")
    redirectHome();
}
