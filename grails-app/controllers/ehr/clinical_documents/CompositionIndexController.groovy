package ehr.clinical_documents

import org.springframework.dao.DataIntegrityViolationException

class CompositionIndexController {

    //static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [compositionIndexInstanceList: CompositionIndex.list(params), compositionIndexInstanceTotal: CompositionIndex.count()]
    }

    def show(Long id) {
        def compositionIndexInstance = CompositionIndex.get(id)
        if (!compositionIndexInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compositionIndex.label', default: 'CompositionIndex'), id])
            redirect(action: "list")
            return
        }

        [compositionIndexInstance: compositionIndexInstance]
    }
}
