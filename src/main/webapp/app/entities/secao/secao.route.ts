import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { SecaoComponent } from './secao.component';
import { SecaoDetailComponent } from './secao-detail.component';
import { SecaoPopupComponent } from './secao-dialog.component';
import { SecaoDeletePopupComponent } from './secao-delete-dialog.component';

export const secaoRoute: Routes = [
    {
        path: 'secao',
        component: SecaoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Secaos'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'secao/:id',
        component: SecaoDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Secaos'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const secaoPopupRoute: Routes = [
    {
        path: 'secao-new',
        component: SecaoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Secaos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'secao/:id/edit',
        component: SecaoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Secaos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'secao/:id/delete',
        component: SecaoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Secaos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
