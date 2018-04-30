import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    LogStatusInventarioService,
    LogStatusInventarioPopupService,
    LogStatusInventarioComponent,
    LogStatusInventarioDetailComponent,
    LogStatusInventarioDialogComponent,
    LogStatusInventarioPopupComponent,
    LogStatusInventarioDeletePopupComponent,
    LogStatusInventarioDeleteDialogComponent,
    logStatusInventarioRoute,
    logStatusInventarioPopupRoute,
} from './';

const ENTITY_STATES = [
    ...logStatusInventarioRoute,
    ...logStatusInventarioPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        LogStatusInventarioComponent,
        LogStatusInventarioDetailComponent,
        LogStatusInventarioDialogComponent,
        LogStatusInventarioDeleteDialogComponent,
        LogStatusInventarioPopupComponent,
        LogStatusInventarioDeletePopupComponent,
    ],
    entryComponents: [
        LogStatusInventarioComponent,
        LogStatusInventarioDialogComponent,
        LogStatusInventarioPopupComponent,
        LogStatusInventarioDeleteDialogComponent,
        LogStatusInventarioDeletePopupComponent,
    ],
    providers: [
        LogStatusInventarioService,
        LogStatusInventarioPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationLogStatusInventarioModule {}
