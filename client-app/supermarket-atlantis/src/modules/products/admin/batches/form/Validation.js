import * as yup from 'yup'
import { YupConfig } from '../../../../shared/index'
import batchTypes from '../../shared/batchTypes'

const schema = (mode = "CREATE") => {
    if (mode == "CREATE") {
        return yup
            .object()
            .shape({
        /*UUID*/ id: yup.string(),
        /*String*/ sku: yup.string().required(),
        /*String*/ upc: yup.string().required(),
        /*String*/ name: yup.string().required(),
        /*String*/ brand: yup.string().required(),
        /*Double*/ minStock: yup.number().min(0).required(),
        /*Double*/ stock: yup.number().positive().required(),
        /*BigDecimal*/ providerPrice: yup.number().moreThan(0).required(),
        /*BigDecimal*/ retailPrice: yup.number().moreThan(0).required(),
        /*String*/ description: yup.string().required(),
        /*String*/ batchDetails: yup.string().required(),
        /*Date*/ expiration: yup.date().min(0)
            .when("type", { is: batchTypes.EXPIRATION.enum, then: s => s.required() }),
        /*BatchType*/ type: yup.mixed().oneOf([batchTypes.DEFAULT.enum, batchTypes.EXPIRATION.enum]).required(),
                providerId: yup.string().min(1)
                    //.transform(value => value === '' ? null : value)
                    //.nullable(false)
                    //.ensure()
                    .required(),
                categories: yup.array().required()
            })
    } else {
        return yup
            .object()
            .shape({
    /*UUID*/ id: yup.string(),
    /*String*/ sku: yup.string().required(),
    /*String*/ upc: yup.string().required(),
    /*String*/ name: yup.string().required(),
    /*String*/ brand: yup.string().required(),
    /*Double*/ stock: yup.number().positive().required(),
    /*BigDecimal*/ providerPrice: yup.number().moreThan(0).required(),
    /*BigDecimal*/ retailPrice: yup.number().moreThan(0).required(),
    /*String*/ description: yup.string().required(),
    /*BatchType*/ type: yup.mixed().oneOf([batchTypes.DEFAULT.enum, batchTypes.EXPIRATION.enum]).required(),
                providerId: yup.string().min(1)
                    //.transform(value => value === '' ? null : value)
                    //.nullable(false)
                    //.ensure()
                    .required(),
                categories: yup.array().required()
            })
    }
}

export default schema;












