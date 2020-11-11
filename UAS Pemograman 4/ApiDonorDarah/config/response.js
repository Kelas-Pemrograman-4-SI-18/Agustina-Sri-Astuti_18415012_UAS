module.exports = {
  commonError : {
      error : true,
      msg : 'Opps... sedang terjadi kesalahan di server !'
  },
  commonErrorMsg : (msg) => {
      return {
          error : true,
          msg : msg
      }
  },
  commonSuccess : {
      error : false,
      msg : 'Yeah... berhasil memuat permintaan ke server.'
  },
  commonSuccessMsg : (msg) => {
      return {
          error : false,
          msg : msg
      }
  },
  commonResult : (data) => {
      return {
          error : false,
          msg : 'Yeah... berhasil memuat data.',
          data : data
      }
  }
};
