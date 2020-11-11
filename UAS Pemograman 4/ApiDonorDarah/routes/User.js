const router = require('express').Router()
const userController = require('../controller/User.js')

router.get("/show", (req, res) => {
    userController.lihatData()
        .then((result) => res.json(result))
        .catch((err) => res.json(err))
})

router.get("/show/:id", (req, res) => {
    userController.lihatDetailData(req.params.id)
        .then((result) => res.json(result))
        .catch((err) => res.json(err))
})

router.put("/update/:id", (req, res) => {
    // console.log(req.params.id)
    userController.updateData(req.params.id, req.body)
        .then((result) => res.json(result))
        .catch((err) => res.json(err))
})

router.post('/registrasi', (req, res) => {
    userController.registrasi(req.body)
        .then((result) => res.json(result))
        .catch((err) => res.json(err))
})

router.post('/login', (req, res) => {
    userController.login(req.body)
        .then((result) => res.json(result))
        .catch((err) => res.json(err))
})

module.exports = router
