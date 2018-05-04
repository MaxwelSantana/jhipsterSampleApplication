import { BaseEntity } from './../../shared';

export class Funcionario implements BaseEntity {
    constructor(
        public id?: number,
        public nome?: string,
        public cpf?: string,
        public inventarios?: BaseEntity[],
        public trasmissoes?: BaseEntity[],
    ) {
    }
}
