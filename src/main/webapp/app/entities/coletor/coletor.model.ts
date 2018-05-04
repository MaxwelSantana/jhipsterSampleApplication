import { BaseEntity } from './../../shared';

export class Coletor implements BaseEntity {
    constructor(
        public id?: number,
        public descricao?: string,
        public trasmissoes?: BaseEntity[],
    ) {
    }
}
