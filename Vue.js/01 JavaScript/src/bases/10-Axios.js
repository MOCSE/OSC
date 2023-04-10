import axios from "axios";


const apiKey = "fhLce7ONBJKc9JgT3r3CjP2gmuCxUQAZ";
//https://api.giphy.com/v1/gifs/random?api_key=${apiKey}

const giphyApi = axios.create({
    baseURL: "https://api.giphy.com/v1/gifs",
    params: {
        api_key: apiKey 
    }
})

giphyApi.get("/random")
    .then(resp => {
        console.log(resp.data.data.images.original.url)
        const url = resp.data.data.images.original.url

        const img = document.createElement("img")
        img.src = url

        document.body.append(img)
    })