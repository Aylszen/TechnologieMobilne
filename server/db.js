const mongodb = require('mongodb').MongoClient;
const ObjectId = require('mongodb').ObjectId;

const config = require('./config');

const dbName = 'technologiemobilne';
let dbClient, usersCollection;

mongodb.connect(config.dbConnUrl, (err, client) => {
    if (err) {
        //console.error(err);
        throw err;
    }

    let db = client.db(dbName);
    usersCollection = db.collection('users');
});

module.exports = {
    users: {
        create: (user) => {
            return usersCollection.insertOne(user);
        },
        update: () => {

        },
        delete: () => {

        },
        getAll: () => {
          var getAllUsers = new Promise(function(resolve, reject) {
          usersCollection.find({}).toArray(function(err, result) {
            if (err) throw err;
            resolve(result);
            });
  });
  return getAllUsers;
},
        findById: (id) => {
            return usersCollection.findOne({_id: id});
        },
        findByUsername: (username) => {
            return usersCollection.findOne({username: username});
        }
    },
};
