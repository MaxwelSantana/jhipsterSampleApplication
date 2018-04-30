import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    CadastroService,
    CadastroPopupService,
    CadastroComponent,
    CadastroDetailComponent,
    CadastroDialogComponent,
    CadastroPopupComponent,
    CadastroDeletePopupComponent,
    CadastroDeleteDialogComponent,
    cadastroRoute,
    cadastroPopupRoute,
} from './';

const ENTITY_STATES = [
    ...cadastroRoute,
    ...cadastroPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CadastroComponent,
        CadastroDetailComponent,
        CadastroDialogComponent,
        CadastroDeleteDialogComponent,
        CadastroPopupComponent,
        CadastroDeletePopupComponent,
    ],
    entryComponents: [
        CadastroComponent,
        CadastroDialogComponent,
        CadastroPopupComponent,
        CadastroDeleteDialogComponent,
        CadastroDeletePopupComponent,
    ],
    providers: [
        CadastroService,
        CadastroPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationCadastroModule {}
