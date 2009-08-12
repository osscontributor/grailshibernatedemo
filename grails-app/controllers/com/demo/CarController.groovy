

package com.demo

class CarController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ carInstanceList: Car.list( params ), carInstanceTotal: Car.count() ]
    }

    def show = {
        def carInstance = Car.get( params.id )

        if(!carInstance) {
            flash.message = "Car not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ carInstance : carInstance ] }
    }

    def delete = {
        def carInstance = Car.get( params.id )
        if(carInstance) {
            try {
                carInstance.delete(flush:true)
                flash.message = "Car ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Car ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "Car not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def carInstance = Car.get( params.id )

        if(!carInstance) {
            flash.message = "Car not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ carInstance : carInstance ]
        }
    }

    def update = {
        def carInstance = Car.get( params.id )
        if(carInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(carInstance.version > version) {
                    
                    carInstance.errors.rejectValue("version", "car.optimistic.locking.failure", "Another user has updated this Car while you were editing.")
                    render(view:'edit',model:[carInstance:carInstance])
                    return
                }
            }
            carInstance.properties = params
            if(!carInstance.hasErrors() && carInstance.save()) {
                flash.message = "Car ${params.id} updated"
                redirect(action:show,id:carInstance.id)
            }
            else {
                render(view:'edit',model:[carInstance:carInstance])
            }
        }
        else {
            flash.message = "Car not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def carInstance = new Car()
        carInstance.properties = params
        return ['carInstance':carInstance]
    }

    def save = {
        def carInstance = new Car(params)
        if(!carInstance.hasErrors() && carInstance.save()) {
            flash.message = "Car ${carInstance.id} created"
            redirect(action:show,id:carInstance.id)
        }
        else {
            render(view:'create',model:[carInstance:carInstance])
        }
    }
}
