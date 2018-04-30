import { BaseEntity } from './../../shared';

export class StatusInventario implements BaseEntity {
    constructor(
        public id?: number,
        public descricao?: string,
    ) {
    }
}
