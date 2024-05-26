const mongoose = require('mongoose');

const dataSchema = new mongoose.Schema({
    code: {
        required: true,
        type: String
    },
    title: {
        required: true,
        type: String
    },
    description: {
        required: true,
        type: String
    },
    category: {
        required: true,
        type: String
    },
    topics: [{
        title : String
    }]
})

module.exports = mongoose.model('Course', dataSchema)