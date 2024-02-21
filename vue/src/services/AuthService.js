import axios from 'axios';

async function login(user) {
  return await axios.post('/login', user)
}
async function register(user) {
  return await axios.post('/register', user)
}

export { register, login }


// import axios from 'axios';

// const http = axios.create({
//     baseURL: 'http://localhost:9000'
// })

// async function login(user){
//    const loggedUser = await http.post('/login', user)
//    return loggedUser
// }

// async function register(user) {
//     const registeredUser = await http.post('/register', user)
//     return registeredUser
//   }


// export{login, register}
