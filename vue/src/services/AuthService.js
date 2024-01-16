import axios from 'axios';

async function login(user) {
  return await axios.post('/login', user)
}
async function register(user) {
  return await axios.post('/register', user)
}

export { register, login }