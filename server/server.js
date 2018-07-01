const express = require('express');
const bodyParser = require('body-parser');
const path = require('path');

const db = require('./db.js');
const app = express();
const PORT = 8080;

app.use(bodyParser.json());
console.log(__dirname);
app.use(bodyParser.urlencoded({ extended: true }));
var router = express.Router();

app.get('/users', function(req, res) {
  console.log(`GET`);
  db.users.getAll().then(function(result){
    console.log(result);
    res.json(result);
  });

});

app.post(`/register`, (req, res) => {
    res.set('content-type', 'application/json');
    let body = req.body;
	console.log(req);
	console.log(req.body);
	console.log(req.body.id);
    let newUser = body.user;
                    db.users.create(body.user).then((result) => {
                        if (result.result.ok && result.result.ok === 1) {
                            res.json({valid: true, info: 'User was successfully created'});
                        } else {
                            res.json({valid: false, info: 'Database error'});
                        }
                    });
                } );

app.listen(PORT, () => {
    console.log(`Server is listening on port ${PORT}`);
});
