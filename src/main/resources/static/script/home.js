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

async function fetchSodaByCategory(){
    let response = await fetch(apiURL);
}

async function fetchSodaByKeyword(){
    let response = await fetch(apiURL);
}

function populateSodas(sodas){
    let sodaNode = document.getElementById('sodas');
    sodaNode.innerHTML = "";
    sodas.forEach((soda) => {
        const sodaContainer = document.createElement("div");
        sodaContainer.className = "sodaContainer";

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

        sodaContainer.appendChild(sodaTitle);
        sodaContainer.appendChild(sodaImage);
        sodaContainer.appendChild(sodaPrice);
        sodaContainer.appendChild(sodaDescription);
        sodaNode.appendChild(sodaContainer); //Add the new soda element to the div on the page
    });
}

//Should always run when page is first loaded
fetchAllSoda();