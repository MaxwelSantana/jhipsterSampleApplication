import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    MapeamentoService,
    MapeamentoPopupService,
    MapeamentoComponent,
    MapeamentoDetailComponent,
    MapeamentoDialogComponent,
    MapeamentoPopupComponent,
    MapeamentoDeletePopupComponent,
    MapeamentoDeleteDialogComponent,
    mapeamentoRoute,
    mapeamentoPopupRoute,
} from './';

const ENTITY_STATES = [
    ...mapeamentoRoute,
    ...mapeamentoPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        MapeamentoComponent,
        MapeamentoDetailComponent,
        MapeamentoDialogComponent,
        MapeamentoDeleteDialogComponent,
        MapeamentoPopupComponent,
        MapeamentoDeletePopupComponent,
    ],
    entryComponents: [
        MapeamentoComponent,
        MapeamentoDialogComponent,
        MapeamentoPopupComponent,
        MapeamentoDeleteDialogComponent,
        MapeamentoDeletePopupComponent,
    ],
    providers: [
        MapeamentoService,
        MapeamentoPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationMapeamentoModule {}
