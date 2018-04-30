import { BaseEntity } from './../../shared';

export class StatusContagem implements BaseEntity {
    constructor(
        public id?: number,
        public descricao?: string,
    ) {
    }
}
