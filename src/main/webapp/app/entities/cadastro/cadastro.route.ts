import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { CadastroComponent } from './cadastro.component';
import { CadastroDetailComponent } from './cadastro-detail.component';
import { CadastroPopupComponent } from './cadastro-dialog.component';
import { CadastroDeletePopupComponent } from './cadastro-delete-dialog.component';

export const cadastroRoute: Routes = [
    {
        path: 'cadastro',
        component: CadastroComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Cadastros'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'cadastro/:id',
        component: CadastroDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Cadastros'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cadastroPopupRoute: Routes = [
    {
        path: 'cadastro-new',
        component: CadastroPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Cadastros'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'cadastro/:id/edit',
        component: CadastroPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Cadastros'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'cadastro/:id/delete',
        component: CadastroDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Cadastros'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
