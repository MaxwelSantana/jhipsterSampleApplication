import { BaseEntity } from './../../shared';

export class Funcionario implements BaseEntity {
    constructor(
        public id?: number,
        public idFuncionario?: number,
        public nome?: string,
        public cpf?: string,
    ) {
    }
}
