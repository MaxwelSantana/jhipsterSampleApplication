import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    CampoCustomizavelColetaService,
    CampoCustomizavelColetaPopupService,
    CampoCustomizavelColetaComponent,
    CampoCustomizavelColetaDetailComponent,
    CampoCustomizavelColetaDialogComponent,
    CampoCustomizavelColetaPopupComponent,
    CampoCustomizavelColetaDeletePopupComponent,
    CampoCustomizavelColetaDeleteDialogComponent,
    campoCustomizavelColetaRoute,
    campoCustomizavelColetaPopupRoute,
} from './';

const ENTITY_STATES = [
    ...campoCustomizavelColetaRoute,
    ...campoCustomizavelColetaPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CampoCustomizavelColetaComponent,
        CampoCustomizavelColetaDetailComponent,
        CampoCustomizavelColetaDialogComponent,
        CampoCustomizavelColetaDeleteDialogComponent,
        CampoCustomizavelColetaPopupComponent,
        CampoCustomizavelColetaDeletePopupComponent,
    ],
    entryComponents: [
        CampoCustomizavelColetaComponent,
        CampoCustomizavelColetaDialogComponent,
        CampoCustomizavelColetaPopupComponent,
        CampoCustomizavelColetaDeleteDialogComponent,
        CampoCustomizavelColetaDeletePopupComponent,
    ],
    providers: [
        CampoCustomizavelColetaService,
        CampoCustomizavelColetaPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationCampoCustomizavelColetaModule {}
