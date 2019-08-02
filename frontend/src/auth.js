
import axios from 'axios'

export default {
  login (name, pass, cb) {
    if (localStorage.token) {
      if (cb) cb(true)
      return
    }
    pretendRequest(name, pass, (res) => {
      if (res.authenticated) {
        localStorage.token = res.token
        if (cb) cb(true)
        this.onChange(true)
      } else {
        if (cb) cb(false)
        this.onChange(false)
      }
    })
  },

  logout (cb) {
    delete localStorage.token
    if (cb) cb()
    this.onChange(false)
  },

  loggedIn () {
    return localStorage.token
  },

  onChange () {}
}






function pretendRequest (name, pass, cb) {
  axios.post(`/login?username=${name}&password=${pass}`)
      .then((response => {
    cb({
      authenticated: true,
      token: Math.random().toString(36).substring(7)
    })

  })).catch((error) => {
    cb({ authenticated: false })
  })
}
