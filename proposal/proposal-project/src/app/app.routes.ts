import { Routes } from '@angular/router';
import { ViewTeacherComponent } from './components/view-teacher/view-teacher.component';
import { ViewStudentComponent } from './components/view-student/view-student.component';
import { AddTeacherComponent } from './components/add-teacher/add-teacher.component';
import { AddStudentComponent } from './components/add-student/add-student.component';

export const routes: Routes = [
    { path: 'view-teacher', component: ViewTeacherComponent },
    { path: 'view-student', component: ViewStudentComponent },
    { path: 'add-teacher', component: AddTeacherComponent },
    { path: 'add-student', component: AddStudentComponent },
];
