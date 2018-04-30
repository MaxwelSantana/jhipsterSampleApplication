import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    CodigoCustomizavelService,
    CodigoCustomizavelPopupService,
    CodigoCustomizavelComponent,
    CodigoCustomizavelDetailComponent,
    CodigoCustomizavelDialogComponent,
    CodigoCustomizavelPopupComponent,
    CodigoCustomizavelDeletePopupComponent,
    CodigoCustomizavelDeleteDialogComponent,
    codigoCustomizavelRoute,
    codigoCustomizavelPopupRoute,
} from './';

const ENTITY_STATES = [
    ...codigoCustomizavelRoute,
    ...codigoCustomizavelPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CodigoCustomizavelComponent,
        CodigoCustomizavelDetailComponent,
        CodigoCustomizavelDialogComponent,
        CodigoCustomizavelDeleteDialogComponent,
        CodigoCustomizavelPopupComponent,
        CodigoCustomizavelDeletePopupComponent,
    ],
    entryComponents: [
        CodigoCustomizavelComponent,
        CodigoCustomizavelDialogComponent,
        CodigoCustomizavelPopupComponent,
        CodigoCustomizavelDeleteDialogComponent,
        CodigoCustomizavelDeletePopupComponent,
    ],
    providers: [
        CodigoCustomizavelService,
        CodigoCustomizavelPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationCodigoCustomizavelModule {}
