import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    ColetorService,
    ColetorPopupService,
    ColetorComponent,
    ColetorDetailComponent,
    ColetorDialogComponent,
    ColetorPopupComponent,
    ColetorDeletePopupComponent,
    ColetorDeleteDialogComponent,
    coletorRoute,
    coletorPopupRoute,
} from './';

const ENTITY_STATES = [
    ...coletorRoute,
    ...coletorPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ColetorComponent,
        ColetorDetailComponent,
        ColetorDialogComponent,
        ColetorDeleteDialogComponent,
        ColetorPopupComponent,
        ColetorDeletePopupComponent,
    ],
    entryComponents: [
        ColetorComponent,
        ColetorDialogComponent,
        ColetorPopupComponent,
        ColetorDeleteDialogComponent,
        ColetorDeletePopupComponent,
    ],
    providers: [
        ColetorService,
        ColetorPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationColetorModule {}
