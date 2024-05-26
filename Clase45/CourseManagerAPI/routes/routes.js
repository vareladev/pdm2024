const express = require('express');

const Model = require('../model/model');
const router = express.Router()

//Post Method
router.post('/postcourse', async (req, res) => {
    const data = new Model({
        code: req.body.code,
        title: req.body.title,
        description: req.body.description,
        category: req.body.category,
        topics: req.body.topics,
    })

    try {
        const dataToSave = await data.save();
        res.status(200).json({"result" : "ok"})
    }
    catch (error) {
        res.status(400).json({message: error.message})
    }
})

router.get('/getallcourses', async (req, res) => {
    try{
        const data = await Model.find();
        res.json(data)
    }
    catch(error){
        res.status(500).json({message: error.message})
    }
})

//Get by ID Method
router.get('/getcourse', async (req, res) => {
    try{
        const data = await Model.findById(req.query.id);
        res.json(data)
    }
    catch(error){
        res.status(500).json({message: error.message})
    }
})


//Update by ID Method
router.patch('/updatecourse', async (req, res) => {
    try {
        const id = req.query.id;
        const updatedData = req.body;
        const options = { new: true };

        const result = await Model.findByIdAndUpdate(
            id, updatedData, options
        )

        res.json({"result" : "ok"})
    }
    catch (error) {
        res.status(400).json({ message: error.message })
    }
})

//Delete by ID Method
router.delete('/deletecourse', async (req, res) => {
    try {
        const id = req.query.id;
        const data = await Model.findByIdAndDelete(id)
        res.json({"result" : "ok"})
    }
    catch (error) {
        res.status(400).json({ message: error.message })
    }
})


module.exports = router;


