import { BaseEntity } from './../../shared';

export class LogStatusInventario implements BaseEntity {
    constructor(
        public id?: number,
        public timeStamp?: any,
        public inventario?: BaseEntity,
        public statusInventario?: BaseEntity,
    ) {
    }
}
