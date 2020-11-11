const multer = require('multer')
const fs = require('fs')
const router = require('express').Router()
const darah = require('../controller/Darah')

var storage = multer.diskStorage({
    filename : function (req, file, cb) {
        let ext = file.originalname.substring(
            file.originalname.lastIndexOf("."),
            file.originalname.length
        )
        cb(null, Date.now() + ext);
    },
    destination : function (req, file, cb) {
        cb(null, './photos')
    }
})

var upload = multer({storage : storage}).single("photos")

router.post("/input", (req, res) => {
    darah.inputDataDarah(req.body)
        .then((result) => res.json(result))
        .catch((err) => res.json(err))
})

router.get("/show", (req, res) => {
    darah.lihatDataDarah()
        .then((result) => res.json(result))
        .catch((err) => res.json(err))
})

router.put("/update/:id", (req, res) => {
        darah.updateDarah(req.params.id, req.body)
        .then((result) => res.json(result))
        .catch((err) => res.json(err))
})

router.get("/show/:id", (req, res) => {
    darah.lihatDetailDataDarah(req.params.id)
        .then((result) => res.json(result))
        .catch((err) => res.json(err))
})

router.get("/buy/:id", (req, res) => {
    darah.buyDarah(req.params.id)
        .then((result) => res.json(result))
        .catch((err) => res.json(err))
})

router.get("/add/:id", (req, res) => {
    darah.addDarah(req.params.id)
        .then((result) => res.json(result))
        .catch((err) => res.json(err))
})

router.delete("/delete/:id", (req, res) => {
    darah.deleteDataDarah(req.params.id)
        .then((result) => res.json(result))
        .catch((err) => res.json(err))
})

module.exports = router
