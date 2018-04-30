import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    TipoContagemService,
    TipoContagemPopupService,
    TipoContagemComponent,
    TipoContagemDetailComponent,
    TipoContagemDialogComponent,
    TipoContagemPopupComponent,
    TipoContagemDeletePopupComponent,
    TipoContagemDeleteDialogComponent,
    tipoContagemRoute,
    tipoContagemPopupRoute,
} from './';

const ENTITY_STATES = [
    ...tipoContagemRoute,
    ...tipoContagemPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        TipoContagemComponent,
        TipoContagemDetailComponent,
        TipoContagemDialogComponent,
        TipoContagemDeleteDialogComponent,
        TipoContagemPopupComponent,
        TipoContagemDeletePopupComponent,
    ],
    entryComponents: [
        TipoContagemComponent,
        TipoContagemDialogComponent,
        TipoContagemPopupComponent,
        TipoContagemDeleteDialogComponent,
        TipoContagemDeletePopupComponent,
    ],
    providers: [
        TipoContagemService,
        TipoContagemPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationTipoContagemModule {}
