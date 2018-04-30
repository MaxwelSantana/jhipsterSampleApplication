import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    CampoCustomizavelCadastroService,
    CampoCustomizavelCadastroPopupService,
    CampoCustomizavelCadastroComponent,
    CampoCustomizavelCadastroDetailComponent,
    CampoCustomizavelCadastroDialogComponent,
    CampoCustomizavelCadastroPopupComponent,
    CampoCustomizavelCadastroDeletePopupComponent,
    CampoCustomizavelCadastroDeleteDialogComponent,
    campoCustomizavelCadastroRoute,
    campoCustomizavelCadastroPopupRoute,
} from './';

const ENTITY_STATES = [
    ...campoCustomizavelCadastroRoute,
    ...campoCustomizavelCadastroPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CampoCustomizavelCadastroComponent,
        CampoCustomizavelCadastroDetailComponent,
        CampoCustomizavelCadastroDialogComponent,
        CampoCustomizavelCadastroDeleteDialogComponent,
        CampoCustomizavelCadastroPopupComponent,
        CampoCustomizavelCadastroDeletePopupComponent,
    ],
    entryComponents: [
        CampoCustomizavelCadastroComponent,
        CampoCustomizavelCadastroDialogComponent,
        CampoCustomizavelCadastroPopupComponent,
        CampoCustomizavelCadastroDeleteDialogComponent,
        CampoCustomizavelCadastroDeletePopupComponent,
    ],
    providers: [
        CampoCustomizavelCadastroService,
        CampoCustomizavelCadastroPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationCampoCustomizavelCadastroModule {}
