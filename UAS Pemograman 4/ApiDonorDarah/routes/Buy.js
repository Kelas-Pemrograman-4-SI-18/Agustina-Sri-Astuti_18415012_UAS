const multer = require('multer')
const fs = require('fs')
const router = require('express').Router()
const buyer = require('../controller/Buy')

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
    buyer.inputDataBuyer(req.body)
        .then((result) => res.json(result))
        .catch((err) => res.json(err))
})

router.get("/show", (req, res) => {
    buyer.lihatDataBuyer()
        .then((result) => res.json(result))
        .catch((err) => res.json(err))
})

router.put("/update/:id", (req, res) => {
    buyer.updateBuyer(req.params.id, req.body)
        .then((result) => res.json(result))
        .catch((err) => res.json(err))
})

router.get("/show/:id", (req, res) => {
    buyer.lihatDetailDataBuyer(req.params.id)
        .then((result) => res.json(result))
        .catch((err) => res.json(err))
})

router.get("/buy/:id", (req, res) => {
    buyer.buyBuyer(req.params.id)
        .then((result) => res.json(result))
        .catch((err) => res.json(err))
})

router.post("/add", (req, res) => {
    buyer.beli(req.body)
        .then((result) => res.json(result))
        .catch((err) => res.json(err))
})

router.delete("/delete/:id", (req, res) => {
    buyer.deleteDataBuyer(req.params.id)
        .then((result) => res.json(result))
        .catch((err) => res.json(err))
})

module.exports = router
