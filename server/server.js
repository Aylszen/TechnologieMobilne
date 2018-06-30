const express = require('express');
const bodyParser = require('body-parser');
const path = require('path');

const db = require('./db.js');

const app = express();

const PORT = 8080;
const BASE_PATH = '/trello_copy_app/api';

app.use(bodyParser.json());
console.log(__dirname);
app.use(bodyParser.urlencoded({ extended: true }));
var router = express.Router();
 
app.get('/dupa', function(req, res) {
	console.log(`GET`);
	res.json([{user:{id: 1234, name: "KrzysztofKita"}}])
    
});

app.post(`/register`, (req, res) => {
    res.set('content-type', 'application/json');
    let body = req.body;
	console.log(req);
	console.log(req.body);
	console.log(req.body.id);
    let newUser = body.user;
			console.log(`Weszlo register!!! przed`);
		console.log(`Weszlo register!!!`);
                    db.users.create(body.user).then((result) => {
                        if (result.result.ok && result.result.ok === 1) {
                            res.end(JSON.stringify({valid: true, info: 'User was successfully created'}));
                        } else {
                            res.end(JSON.stringify({valid: false, info: 'Database error'}));
                        }
                    });
                } );
app.post(`/login`, (req, res) => {
    res.set('content-type', 'application/json');
    let body = req.body;
    let user = body.user;
	console.log(`Weszlo!!!`);
    if (user) {
        if (user.username && user.username !== '' && user.password && user.password !== '') {
            db.users.findByUsername(user.username).then((result) => {
                if (result !== null) {
                    if (result.password === user.password) {
                        res.end(JSON.stringify({valid: true, info: 'Password for this user is correct'}));
                    } else {
                        res.end(JSON.stringify({
                            valid: false,
                            info: 'Password for this user is incorrect'
                        }));
                    }
                } else {
                    res.end(JSON.stringify({
                        valid: false,
                        info: 'User with specified username does not exist!'
                    }));
                }
            });
        } else {
            res.end(JSON.stringify({
                valid: false,
                info: 'User object must have all necessary fields which can\'t be empty'
            }));
        }
    } else {
        res.end(JSON.stringify({valid: false, info: 'No user object in request body'}));
    }
});

app.listen(PORT, () => {
    console.log(`Server is listening on port ${PORT}`);
});
