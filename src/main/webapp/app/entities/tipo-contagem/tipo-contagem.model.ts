import { BaseEntity } from './../../shared';

export class TipoContagem implements BaseEntity {
    constructor(
        public id?: number,
        public descricao?: string,
    ) {
    }
}
