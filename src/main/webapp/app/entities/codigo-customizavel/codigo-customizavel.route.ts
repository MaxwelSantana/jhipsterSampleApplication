import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { CodigoCustomizavelComponent } from './codigo-customizavel.component';
import { CodigoCustomizavelDetailComponent } from './codigo-customizavel-detail.component';
import { CodigoCustomizavelPopupComponent } from './codigo-customizavel-dialog.component';
import { CodigoCustomizavelDeletePopupComponent } from './codigo-customizavel-delete-dialog.component';

export const codigoCustomizavelRoute: Routes = [
    {
        path: 'codigo-customizavel',
        component: CodigoCustomizavelComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CodigoCustomizavels'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'codigo-customizavel/:id',
        component: CodigoCustomizavelDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CodigoCustomizavels'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const codigoCustomizavelPopupRoute: Routes = [
    {
        path: 'codigo-customizavel-new',
        component: CodigoCustomizavelPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CodigoCustomizavels'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'codigo-customizavel/:id/edit',
        component: CodigoCustomizavelPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CodigoCustomizavels'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'codigo-customizavel/:id/delete',
        component: CodigoCustomizavelDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CodigoCustomizavels'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
