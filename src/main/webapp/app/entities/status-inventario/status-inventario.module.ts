import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    StatusInventarioService,
    StatusInventarioPopupService,
    StatusInventarioComponent,
    StatusInventarioDetailComponent,
    StatusInventarioDialogComponent,
    StatusInventarioPopupComponent,
    StatusInventarioDeletePopupComponent,
    StatusInventarioDeleteDialogComponent,
    statusInventarioRoute,
    statusInventarioPopupRoute,
} from './';

const ENTITY_STATES = [
    ...statusInventarioRoute,
    ...statusInventarioPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        StatusInventarioComponent,
        StatusInventarioDetailComponent,
        StatusInventarioDialogComponent,
        StatusInventarioDeleteDialogComponent,
        StatusInventarioPopupComponent,
        StatusInventarioDeletePopupComponent,
    ],
    entryComponents: [
        StatusInventarioComponent,
        StatusInventarioDialogComponent,
        StatusInventarioPopupComponent,
        StatusInventarioDeleteDialogComponent,
        StatusInventarioDeletePopupComponent,
    ],
    providers: [
        StatusInventarioService,
        StatusInventarioPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationStatusInventarioModule {}
