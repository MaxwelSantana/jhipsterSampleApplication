import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ColetorComponent } from './coletor.component';
import { ColetorDetailComponent } from './coletor-detail.component';
import { ColetorPopupComponent } from './coletor-dialog.component';
import { ColetorDeletePopupComponent } from './coletor-delete-dialog.component';

export const coletorRoute: Routes = [
    {
        path: 'coletor',
        component: ColetorComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Coletors'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'coletor/:id',
        component: ColetorDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Coletors'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const coletorPopupRoute: Routes = [
    {
        path: 'coletor-new',
        component: ColetorPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Coletors'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'coletor/:id/edit',
        component: ColetorPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Coletors'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'coletor/:id/delete',
        component: ColetorDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Coletors'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
