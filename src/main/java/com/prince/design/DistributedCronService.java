package com.prince.design;

/**
 * https://engineering.quora.com/Quoras-Distributed-Cron-Architecture
 *
 * @author Prince Raj
 */
public class DistributedCronService {

    public static void main(String[] args) {

        /**
         * <pre>
         *  all_frequencies = ['minutely', 'five_minutely', 'hourly', 'daily', 'weekly', 'hourly_rounded', 'daily_rounded']
         *  config = {
         *          'cron1': { 'run': all_frequencies },
         *  'cron2': { 'run': ['minutely'], # even minutes only
         *      'run_stats': all_frequencies },
         *  'cron3': { 'run_non_critical': all_frequencies },
         *  'default': { 'run': all_frequencies }
         *  }
         * </pre>
         */

        // 1. start_time is NULL
        // The job has never run; it's available for reservation.
        // 2. start_time < cycle_start_time and (end_time is NULL or end_time < start_time)
        // The job is not available; it's currently running.
        // 3. start_time < cycle_start_time and end_time >= start_time
        // The job is not running and has not run during this cycle; it's available for reservation.
        // 4. start_time >= cycle_start_time
        // The job has already run during this cycle; it's not available for reservation.
    }
}
