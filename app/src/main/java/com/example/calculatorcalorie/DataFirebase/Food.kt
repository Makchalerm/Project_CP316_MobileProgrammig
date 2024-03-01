package com.example.calculatorcalorie.Data

class Food {
    var image: String? = null
    var cal: String? = null
    var foodName: String? = null

    constructor()
    constructor(image: String?, cal: String?, foodName: String?){
        this.image = image
        this.cal = cal
        this.foodName = foodName
    }
}
