import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { TransmissaoComponent } from './transmissao.component';
import { TransmissaoDetailComponent } from './transmissao-detail.component';
import { TransmissaoPopupComponent } from './transmissao-dialog.component';
import { TransmissaoDeletePopupComponent } from './transmissao-delete-dialog.component';

export const transmissaoRoute: Routes = [
    {
        path: 'transmissao',
        component: TransmissaoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Transmissaos'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'transmissao/:id',
        component: TransmissaoDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Transmissaos'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const transmissaoPopupRoute: Routes = [
    {
        path: 'transmissao-new',
        component: TransmissaoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Transmissaos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'transmissao/:id/edit',
        component: TransmissaoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Transmissaos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'transmissao/:id/delete',
        component: TransmissaoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Transmissaos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
