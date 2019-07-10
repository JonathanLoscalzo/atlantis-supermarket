
import * as yup from 'yup'
import { YupConfig } from '../../../shared/index'

export default yup
    .object()
    .shape({
        name: yup.string().default(""),
        advanceSearch: yup.boolean().default(false),
        minPrice: yup.number().nullable().when(["advanceSearch", "maxPrice"],{
            is:(advanceSearch, maxPrice) => advanceSearch && maxPrice > 0,
            then: yup.number().lessThan(yup.ref("maxPrice"))
        }),
        maxPrice: yup.number().nullable().when(["advanceSearch"],{
            is:(advanceSearch) => advanceSearch == true,
            then: yup.number().moreThan(yup.ref("minPrice"))
        }),
    })