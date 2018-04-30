import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { MotivoAlteracaoComponent } from './motivo-alteracao.component';
import { MotivoAlteracaoDetailComponent } from './motivo-alteracao-detail.component';
import { MotivoAlteracaoPopupComponent } from './motivo-alteracao-dialog.component';
import { MotivoAlteracaoDeletePopupComponent } from './motivo-alteracao-delete-dialog.component';

export const motivoAlteracaoRoute: Routes = [
    {
        path: 'motivo-alteracao',
        component: MotivoAlteracaoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MotivoAlteracaos'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'motivo-alteracao/:id',
        component: MotivoAlteracaoDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MotivoAlteracaos'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const motivoAlteracaoPopupRoute: Routes = [
    {
        path: 'motivo-alteracao-new',
        component: MotivoAlteracaoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MotivoAlteracaos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'motivo-alteracao/:id/edit',
        component: MotivoAlteracaoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MotivoAlteracaos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'motivo-alteracao/:id/delete',
        component: MotivoAlteracaoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MotivoAlteracaos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
