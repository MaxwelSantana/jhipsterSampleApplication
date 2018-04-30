import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    FuncionarioService,
    FuncionarioPopupService,
    FuncionarioComponent,
    FuncionarioDetailComponent,
    FuncionarioDialogComponent,
    FuncionarioPopupComponent,
    FuncionarioDeletePopupComponent,
    FuncionarioDeleteDialogComponent,
    funcionarioRoute,
    funcionarioPopupRoute,
} from './';

const ENTITY_STATES = [
    ...funcionarioRoute,
    ...funcionarioPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        FuncionarioComponent,
        FuncionarioDetailComponent,
        FuncionarioDialogComponent,
        FuncionarioDeleteDialogComponent,
        FuncionarioPopupComponent,
        FuncionarioDeletePopupComponent,
    ],
    entryComponents: [
        FuncionarioComponent,
        FuncionarioDialogComponent,
        FuncionarioPopupComponent,
        FuncionarioDeleteDialogComponent,
        FuncionarioDeletePopupComponent,
    ],
    providers: [
        FuncionarioService,
        FuncionarioPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationFuncionarioModule {}
