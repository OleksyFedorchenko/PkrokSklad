let serverURL = 'http://localhost:8080/';
let token = window.localStorage.getItem('auth_token');

$.ajaxSetup({
    headers : {
        'Authorization' : 'Bearer ' + token
    }
})

function parseJwt (token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace('-', '+').replace('_', '/');
    return JSON.parse(window.atob(base64));
};